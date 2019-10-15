package com.example.testapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

const val DataBaseName ="Data"

class DataBase(private val context: Context) : 
    SQLiteOpenHelper(context, DataBaseName, null, 1){
    
    private fun getRandomSequence(length: Int, min:Int, max:Int): Sequence<Int>{
        return generateSequence(0) { i -> i + 1 }.map { (min..max).random() }
            .distinct().take(length)
    }
    
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    init {
        this.readableDatabase
        this.writableDatabase
    }
    
    fun init() {
        fillTableFromSource("Exercises", R.string.exercisesTable)
        fillTableFromSource("Constant", R.string.constantTable)
        fillTableFromSource("Bodies", R.string.bodiesTable)
        val db = writableDatabase
        for (day in 1..15)
            db?.execSQL("INSERT INTO Days VALUES ($day, 'false', 3)")
    }

    private fun fillTableFromSource(tableName: String, id: Int) {
        val db = this.writableDatabase
        val lines = context.getString(id).split("\n")
            .map { l -> l.split("|").joinToString(",") { w -> "'$w'" } }

        for (line in lines) {
            val query = "INSERT INTO $tableName VALUES ($line)"
            db.execSQL(query)
        }
    }
    
    override fun onCreate(db: SQLiteDatabase?) {
        createExercisesTable(db)
        createConstantTable(db)
        createDaysTable(db)
        createCurrentBodyTable(db)
        createBodiesTable(db)
    }

    private fun createBodiesTable(db: SQLiteDatabase?) {
        val createTable = 
                """ CREATE TABLE Bodies (
                    Name text,
                    Images integer,
                    Type text,
                    Growth integer)
                """
        db?.execSQL(createTable)
    }   

    private fun createExercisesTable(db: SQLiteDatabase?) {
        val exercisesTable =
            """CREATE TABLE Exercises (
            Name text,
            Type integer,
            Desc text,
            Image integer,
            CurrentCount integer,
            MaxCount integer,
            TimerTime integer,
            Increment integer)"""
        db?.execSQL(exercisesTable)
    }

    private fun createDaysTable(db: SQLiteDatabase?) {
        val tableName = "Days"
        val daysTable =
            """ CREATE TABLE $tableName (
            Number integer,
            Done boolean,
            Type integer)"""
        db?.execSQL(daysTable)
    }

    private fun createCurrentBodyTable(db: SQLiteDatabase?) {
        val currentBodyTable =
            """ CREATE TABLE CurrentBody (
                Name text,
                Type integer,
                Image integer)"""
        db?.execSQL(currentBodyTable)
    }

    private fun createConstantTable(db: SQLiteDatabase?) {
        val tableName = "Constant"
        val constantTable =
            """ CREATE TABLE $tableName (
                BodyName text,
                Day integer,
                ExType integer)
            """
        db?.execSQL(constantTable)
    }
    
    private fun getTableData(tableName: String, predicate: String = "") 
            : ArrayList<ArrayList<String>>{
        var result = ArrayList<ArrayList<String>>()
        val db = this.readableDatabase
        val query = "Select * from $tableName $predicate"
        val cursor = db.rawQuery(query,null)
        while (cursor.moveToNext()){
            val line = ArrayList<String>()
            for (col in 0 until cursor.columnCount)
                line.add(cursor.getString(col))
            result.add(line)
        }
        cursor.close()
        db.close()
        return result
    }
    
    fun getRandomExercises(count: Int): ArrayList<Exercise>{
        val lines = getTableData("Exercises")
        val exercises = ArrayList<Exercise>()
        for (index in getRandomSequence(count, 0, lines.count()))
            exercises.add(createExercise(lines[index]))
        return exercises
    }
    
    fun getBodyTypeInfo(type: BodyType): BodyTypeInfo{
        val db = this.readableDatabase
        val query = "SELECT * FROM Bodies WHERE Type = ${type.ordinal} "
        val cursor = db.rawQuery(query, null)   
        if (cursor.moveToFirst()){
            val body = BodyTypeInfo(cursor.getString(0), cursor.getInt(1),
                BodyType.values()[cursor.getInt(2)], cursor.getInt(3))
            cursor.close()
            return body
        }
        throw Exception("There is no bodies!")
    }
    
    fun saveSelectedBody(body: Body){
        val db = this.writableDatabase
        val bodyInfo = "'${body.name}', ${body.bodyType.ordinal}, ${body.imageIndex}"
        db.execSQL("INSERT INTO CurrentBody VALUES ($bodyInfo)")
        db.close()
    }
    
    fun getCurrentBody(): Body{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM CurrentBody", null)
        cursor.moveToFirst()
        val body = Body(cursor.getString(0), cursor.getInt(1), 
            BodyType.values()[cursor.getInt(2)])
        cursor.close()
        db.close()
        return body
    }
    
    fun getProgress(): Progress{
        val db = this.readableDatabase
        val query = "SELECT * FROM Days WHERE Done = 'true'"
        val cursor = db.rawQuery(query, null)
        val doneCount = cursor.count
        cursor.close()
        val daysDone = 100 * doneCount / 30
        val exercisesDone = 100 * doneCount * 12 / 360
        return Progress(daysDone, exercisesDone)
    }
    
    fun saveDoneDay(dayNumber: Int, exercises: ArrayList<Exercise>){
        val db = this.writableDatabase
        db.execSQL("UPDATE Days SET Done = 'true' WHERE Number = $dayNumber")
        val exerciseNames = exercises.joinToString(","){ e -> "'${e.name}'" }
        val query = "UPDATE Exercises SET CurrentCount = CurrentCount + Increment" +
                " WHERE CurrentCount + Increment <= MaxCount AND name in ($exerciseNames)"
        db.execSQL(query)
        db.close()
    }
    
    fun getCommonTraining(): ArrayList<Exercise>{
        val exercises = ArrayList<Exercise>(12)
        for (exType in ExerciseType.values()){
            val type = "WHERE Type = ${exType.ordinal}"
            val exs = getTableData("Exercises", type)
            for (index in getRandomSequence(4, 0, exs.count())){
                exercises.add(createExercise(exs[index]))
            }
        }
        return exercises
    }
    
    private fun createExercise(line : ArrayList<String>): Exercise{
        val exercise = Exercise()
        exercise.name = line[0]
        exercise.type = ExerciseType.values()[line[1].toInt()]
        exercise.desc = line[2]
        exercise.image = line[3].toInt()
        exercise.maxCount = line[4].toInt()
        exercise.timerTime = line[5].toInt()
        exercise.increment = line[6].toInt()
        return exercise
    }
    
    fun getTraining(dayNumber: Int): ArrayList<Exercise>{
        if (dayNumber < 16)
            return getCommonTraining()
        val body = getCurrentBody()
        val db = this.readableDatabase
        val query = "SELECT ExType FROM Constant WHERE BodyName='${body.name}'" +
                "AND Day = $dayNumber"
        val cursor = db.rawQuery(query, null)
        val exType = cursor.getInt(0)
        cursor.close()
        if (exType == ExerciseType.Common.ordinal)
            return getCommonTraining()
        val exercises = ArrayList<Exercise>()
        val predicate = "WHERE Type = $exType"
        val exs = getTableData("Exercises", predicate)
        for (index in getRandomSequence(12, 0, exs.count())){
            exercises.add(createExercise(exs[index]))
        }
        return exercises
    }
}