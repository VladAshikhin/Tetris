package com.om.tetris

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
  var tetris: Tetris? = null

  var timer = Timer()

  var gameStarted = false

  private var stepPeriod = 800L

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    mainContentLayout.post {

      addGameView()

      buttonStartPause.setOnClickListener {
        if (gameStarted) {
          buttonStartPause.text = "Start"
          pauseGameLoop()
          //removeGameView()
        } else {
          buttonStartPause.text = "Pause"
          startGameLoop()
          addGameView()
        }
      }
    }
  }

/*  override fun onDestroy() {
    super.onDestroy()
    pauseGameLoop()
  }*/

  fun addGameView() {
    if (tetris == null) {
      tetris = Tetris(this)
      mainContentLayout.addView(tetris)
    }
  }

  fun removeGameView() {
    mainContentLayout.removeView(tetris)
    tetris = null
  }

  fun startGameLoop() {
    timer = Timer()

    val gameTimerTask = object : TimerTask() {
      override fun run() {
        runOnUiThread {
          tetris?.loop()

          buttonRight.setOnClickListener { moveRight() }

          buttonLeft.setOnClickListener { moveLeft() }

          buttonDown.setOnClickListener { moveDown() }

          buttonRotate.setOnClickListener { rotate() }

          buttonForceDown.setOnClickListener { moveForceDown() }

        }
      }
    }

    timer.schedule(gameTimerTask, stepPeriod, stepPeriod)
    gameStarted = true
  }

  fun moveRight() {
    if (gameStarted) {
      tetris?.moveRight()
    }
  }

  fun moveLeft() {
    if(gameStarted){
      tetris?.moveLeft()
    }
  }

  fun moveDown() {
    if(gameStarted){
      tetris?.moveDown()
    }
  }

    fun rotate() {
      if (gameStarted) {
          tetris?.rotate()
      }
    }

  fun moveForceDown() {
    if(gameStarted) {
      tetris?.moveForceDown()
    }
  }

  fun pauseGameLoop() {
    timer.cancel()
    gameStarted = false
  }
}