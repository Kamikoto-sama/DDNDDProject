package com.example.projectapplication

class Exercise{
    var name: String = ""
    var type: ExerciseType = ExerciseType.Legs
    var desc: String = ""
    var image: Int = -1
    var currentCount = -1
    var maxCount: Int = -1
    var timerTime: Int = -1
    var increment: Int = -1
}

data class BodyTypeInfo(val name: String, val imagesIndex: Int, val type: String, 
                        val growth: Int, val alterName: String, val ownImage: Int)

data class Body(val name: String, val type: String, val imageIndex: Int,
                val alterName: String)

data class Day(var number:Int, var isDone: Boolean, var exType: ExerciseType)

data class Progress(val daysCount:Int, val exercisesCount: Int)

enum class ExerciseType{
    Top,
    Press,
    Legs
}