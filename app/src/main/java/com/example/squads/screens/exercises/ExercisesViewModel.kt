package com.example.squads.screens.exercises

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.squads.screens.exercises.models.ExerciseType

class ExercisesViewModel : ViewModel() {

    // list of all exercises
    private val _exercises = MutableLiveData<List<ExerciseType>>()
    val exercises: LiveData<List<ExerciseType>>
        get() = _exercises

    init {
        getExercises()
    }

    fun getExercises() {
        _exercises.value = listOf(
            ExerciseType(
                1, "Bench Press", "The bench press is one of the best chest exercises to build muscle mass and strength, but other exercises are also beneficial for the chest muscles.",
                "benchpress"
            ),
            ExerciseType(
                2, "Deadlift", "The deadlift is a weight training exercise in which a loaded barbell or bar is lifted off the ground. It is one of the three powerlifting exercises, along with the squat and bench press. Deadlift phases.",
                "deadlift"
            ),
            ExerciseType(
                3, "Squat", "A squat is a strength exercise in which the trainee lowers their hips from a standing position and then stands back up. During the descent of a squat, the hip and knee joints flex while the ankle joint dorsiflexes.",
                "squat"
            ),
            ExerciseType(
                4, "Shoulder Press", "The shoulder press is one of the best exercises for strengthening your shoulders and upper back. The biggest benefactor of the shoulder press is the front portion of your shoulder muscle.",
                "shoulderpress"
            ),
            ExerciseType(
                5, "Lunges", "It involves stepping forward, lowering your body toward the ground, and returning back to the starting position.",
                "lunges"
            ),
            ExerciseType(
                6, "Push Press", "The push press is an overhead press variant that uses the legs to create power. To begin the lift, the legs bend to an athletic dip position, followed by a speedy extension of the body to drive the weight overhead.",
                "pushpress"
            ),
            ExerciseType(
                7, "Thruster", "The thruster is a compound exercise since it uses more than one joint and combines the front squat and overhead press.",
                "thruster"
            ),
        )
    }
}
