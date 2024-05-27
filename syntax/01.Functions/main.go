package main

import "fmt"

// это sliсe (массив динамического размера (пока не укажем цифры внутри квадратных скобок))
// var array []int

// хочу получать функцию с какой-то конкретной сортировкой и логикой сравнения чисел
func SelectionSort(compare func(a, b int) int) (sortFunction func(array [5]int) (sortedArray [5]int)) {

	sortFunction = func(array [5]int) (sortedArray [5]int) {
		sortedArray = array

		for i := 0; i < len(sortedArray); i++ {
			minIndex := i
			for j := i; j < len(sortedArray); j++ {
				if compare(sortedArray[j], sortedArray[minIndex]) < 0 {
					minIndex = j
				}
			}
			temp := sortedArray[i]
			sortedArray[i] = sortedArray[minIndex]
			sortedArray[minIndex] = temp
		}
	
		return
	}

	return
}

func BubbleSort(compare func(a, b int) int) (sortFunction func(array [5]int) (sortedArray [5]int)) {

	sortFunction = func(array [5]int) (sortedArray [5]int) {
		for i := len(array) - 1; i >= 0; i-- {
			for j := 0; j < i; j++ {
				if compare(array[j], array[j+1]) >= 0 {
					temp := array[j]
					array[j] = array[j+1]
					array[j+1] = temp
				}
			}
		}
		
		sortedArray = array
		return
	}

	return
}

func Sort(
	compare func(a, b int) int,
	sortAlg func(compare func(a, b int) int) func([5]int) [5]int,
		array [5]int) (sortedArray [5]int) {
	sortFunction := sortAlg(compare)
	return sortFunction(array)
}


func main() {
	array := [5]int{5, 1, 6, -10, 11}

	compareAsc := func(a, b int) int {
		return a - b
	}

	compareDesc := func(a, b int) int {
		return b - a
	}

	_ = compareDesc

	sortedArray  := Sort(compareAsc, BubbleSort, array)
	fmt.Printf("%v\n", sortedArray)
}
