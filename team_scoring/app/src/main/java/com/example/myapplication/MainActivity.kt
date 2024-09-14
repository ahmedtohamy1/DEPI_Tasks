package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var teamAScore = 0
    private var teamBScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Team A buttons
        binding.btnTeamAInc.setOnClickListener {
            teamAScore++
            updateScores()
        }

        binding.btnTeamADec.setOnClickListener {
            if (teamAScore > 0) teamAScore--
            updateScores()
        }

        // Team B buttons
        binding.btnTeamBInc.setOnClickListener {
            teamBScore++
            updateScores()
        }

        binding.btnTeamBDec.setOnClickListener {
            if (teamBScore > 0) teamBScore--
            updateScores()
        }

        // Update the score and winner on initial launch
        updateScores()
    }

    private fun updateScores() {
        // Update the score displays
        binding.txtTeamAScore.text = teamAScore.toString()
        binding.txtTeamBScore.text = teamBScore.toString()

        // Determine and display the winner
        when {
            teamAScore > teamBScore -> binding.txtWinner.text = "Team A is Winning!"
            teamAScore < teamBScore -> binding.txtWinner.text = "Team B is Winning!"
            else -> binding.txtWinner.text = "It's a Tie!"
        }
    }
}
