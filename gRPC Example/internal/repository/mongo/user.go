package mongo

import (
	"context"
	"fmt"
	"gRPC_Example/internal/core"
	"time"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
)

type UserRepository struct {
	collection *mongo.Collection
}

func NewUserRepository(collection *mongo.Collection) *UserRepository {
	return &UserRepository{collection: collection}
}

func (repository *UserRepository) GetById(ctx context.Context, id string) (*core.User, error) {
	ctxTimeout, cancel := context.WithTimeout(ctx, time.Second * 5)
	defer cancel()

	userChannel := make(chan *core.User)
	var err error
	
	fmt.Println("user_id =", id)

	go func() {
		err = repository.retrieveUser(ctxTimeout, id, userChannel)
	}()

	if err != nil {
		return nil, err
	}

	var user *core.User

	select {
	case <- ctxTimeout.Done():
		fmt.Println("Processing timeout in Mongo")
		break
	case user = <- userChannel:
		fmt.Println("Finished processing in Mongo")
	}

	return user, nil
}

func (repository *UserRepository) retrieveUser (ctx context.Context, id string, channel chan<- *core.User) (err error) {
	objectId, err := primitive.ObjectIDFromHex(id)
	fmt.Println("object_id =", objectId)
	fmt.Println("context =", ctx)

	if err != nil {
		return err
	}

	user := &core.User{}
	fmt.Println("repository_collection =", repository.collection)
	err = repository.collection.FindOne(ctx, bson.M{"_id": objectId}).Decode(user)

	fmt.Println("error =", err)

	if err != nil {
		return err
	}

	channel <- user

	return nil
}
