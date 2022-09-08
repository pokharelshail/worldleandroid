package com.myprojects.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var randomWord = FourLetterWordList.getRandomFourLetterWord()
    private var gameWon = false






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fourLetterWords =
            "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care"
        val splitup = fourLetterWords.split(",")
        var counter = 0
        val submitButton = findViewById<Button>(R.id.submitButton)
        val guessBox = findViewById<EditText>(R.id.guessBox)
        val guess1 = findViewById<TextView>(R.id.guess1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        val hint1 = findViewById<TextView>(R.id.hint1)
        val hint2 = findViewById<TextView>(R.id.hint2)
        val hint3 = findViewById<TextView>(R.id.hint3)
        val answer = findViewById<TextView>(R.id.answer)


        // This runs if submitButton is Clicked.
        submitButton.setOnClickListener(){

            Toast.makeText(this@MainActivity, "Submitted", Toast.LENGTH_SHORT).show();



            when (counter) {
                0 -> {
                    counter +=1
                    checkWordandUpdateX(guess1,randomWord,hint1)
                }
                1 -> {
                    counter +=1
                    checkWordandUpdateX(guess2,randomWord,hint2)
                }
                2 -> {
                    counter +=1
                    checkWordandUpdateX(guess3,randomWord,hint3)
                }
                else ->
                {
                Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_SHORT).show();
                }
            }

            if (counter >2 && !gameWon){
                answer.text = randomWord
            }

        }
    }


    private fun checkWordandUpdateX(
        guess: TextView?,
        randomWord: String,
        hint: TextView?, )
    {

        Log.d("RandomWord",randomWord)
        // Updates the empty string "" next to Guess#X    ->  Guess#X      Ball
        if (guess != null) {
            guess.text = guessBox.text
        }
        // Clear the GuessBox where you guess after hitting submit
        guessBox.text.clear()

        // Splits the string "Area,Army,Baby,Back,..." to a list [Area,Army,Baby,Back,...]
        val letters = guess!!.text.split("")

        var newText= ""
        for(letter in randomWord.iterator()){
            if (letter.toString()  in letters)
                newText += letter
            else
                newText += "X"
        }

        if (hint != null) {
            hint.text = newText
        }

        if (newText == randomWord){
            answer.text = "YOU WON"
            gameWon = true
        }

    }


    object FourLetterWordList {
        // List of most common 4 letter words from: https://7esl.com/4-letter-words/
        val fourLetterWords =
            "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care"
        // Returns a list of four letter words as a list
        fun getAllFourLetterWords(): List<String> {
            return fourLetterWords.split(",")
        }
        // Returns a random four letter word from the list in all caps
        fun getRandomFourLetterWord(): String {
            val allWords = getAllFourLetterWords()
            val randomNumber = (0..allWords.size).shuffled().last()
            return allWords[randomNumber]
        }
    }



}