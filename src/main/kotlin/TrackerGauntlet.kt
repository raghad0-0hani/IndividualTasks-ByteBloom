package org.example
fun main(){
    priorityDispatcher()
    wayPointsReverser()
    maxWeightFilter()
    checkTransitCodePalindrome()
    binarySearchTrackingId(intArrayOf(2,4,6,8,10,12,14),10)

}
 //Problem 01...
fun priorityDispatcher(){
     // Loop through package IDs from 1 to 50
    for (packageIndex in 1..50){
        // Check if the number is divisible by both 3 and 5
        if (packageIndex %3 ==0 && packageIndex %5==0){
            println("PriorityExpress")
        }
        // Check if the number is divisible by 3 only
        else if(packageIndex %3 ==0){
            println("Express")
        }
        // Check if the number is divisible by 5 only
        else if(packageIndex %5 ==0){
            println("Freight")
        }
        // If none of the conditions match, print the number itself
        else{
            println(packageIndex)
        }
    }
}


//Problem 02...
fun wayPointsReverser() {
    // Store the route as one string
    val routeDescription = "HubA→ HubB→ HubC"
    // Split the string into separate waypoints(words)
    val wayPoints = routeDescription.split("→")
    // Start from the last waypoint and move to the first
    for (wayPointsIndex in wayPoints.size - 1 downTo 0) {
        print(wayPoints[wayPointsIndex])
// Print the arrow only if this is not the last printed waypoint
        if (wayPointsIndex != 0) {
            print(" → ")
        }
    }
    println()
}


//Problem 03...
fun maxWeightFilter() {
    // Store the package weights (-1.0 means invalid weight)
    val packageWeights = doubleArrayOf(10.8, -1.0, 3.9, 15.7, -1.0, 12.6)
    // Start with the smallest possible value(-∞)
    var highestValidWeight = Double.NEGATIVE_INFINITY
    // Store the highest valid weight
    for (currentWeight in packageWeights) {
        if (currentWeight != -1.0 && currentWeight > highestValidWeight) {
            highestValidWeight = currentWeight
        }
    }
    println("Highest Valid Weight = $highestValidWeight")
    println()
}


//Problem 04...
fun checkTransitCodePalindrome() {
    val transitCode = "TR808RT"
    // convert to uppercase (just in case it is lowercase)
    val normalized = transitCode.uppercase()
    // reverse the string
    val reversed = normalized.reversed()
    // compare original with reversed
    val isPalindrome = false
    if (normalized == reversed){
        println("Palindrome: $isPalindrome")
        println()
    }

}

//Problem 05...
fun binarySearchTrackingId(
    sortedTrackingIds: IntArray,
    targetTrackingId: Int
): Int {

    // Step 1: Define the search range (start and end of array)
    var leftBoundary = 0
    var rightBoundary = sortedTrackingIds.lastIndex

    // Step 2: This variable counts how many comparisons we did (for tracing)
    var comparisonIteration = 1

    // Step 3: Keep searching while there is still a valid range
    while (leftBoundary <= rightBoundary) {

        // Step 4: Find the middle index of current range
        val middleIndex = (leftBoundary + rightBoundary) / 2

        // Print current step for learning purpose
        println(" Iteration $comparisonIteration")
        println("Left = $leftBoundary, Right = $rightBoundary")
        println("Middle Index = $middleIndex")
        println("Checking value = ${sortedTrackingIds[middleIndex]}")
        println("....................................")

        // Step 5: If we found the target, return its index
        if (sortedTrackingIds[middleIndex] == targetTrackingId) {
            println(" Found the target!! ")
            return middleIndex
        }

        // Step 6: If target is bigger, search in the RIGHT half
        if (sortedTrackingIds[middleIndex] < targetTrackingId) {
            leftBoundary = middleIndex + 1
        }
        // Step 7: If target is smaller, search in the LEFT half
        else {
            rightBoundary = middleIndex - 1
        }

        // Step 8: Move to next iteration
        comparisonIteration++
    }

    // Step 9: If we exit loop, target is not found
    println(" Target not found in array :( ")
    return -1
}






