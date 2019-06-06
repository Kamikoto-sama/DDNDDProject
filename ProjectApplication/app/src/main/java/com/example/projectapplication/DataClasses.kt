package com.example.testapp

class Exercise{
    var name: String = ""
    var type: ExerciseType = ExerciseType.Common
    var desc: String = ""
    var image: Int = -1
    var maxCount: Int = -1
    var timerTime: Int = -1
    var increment: Int = -1
}

data class BodyTypeInfo(val name: String, val imagesIndex: Int,
                        val type: BodyType, val growth: Int)

data class Body(val name: String, val imageIndex: Int, val bodyType: BodyType)

data class Day(var number:Int, var isDone: Boolean, var exType: ExerciseType)

data class Progress(val daysCount:Int, val exercisesCount: Int)

enum class ExerciseType{
    Top,
    Press,
    Legs,
    Common;
}

enum class BodyType{
    Mesomorph,
    Ectomorph,
    Endomorph
}