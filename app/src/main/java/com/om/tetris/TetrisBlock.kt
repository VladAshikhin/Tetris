package com.om.tetris

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import com.om.tetris.shapes.*
import com.om.tetris.validator.BlockMovementValidator
import java.util.*

open class TetrisBlock {
  val cellHeight = 70
  val cellWidth = 70
  val spacing = 30 // == 10dp

  var position = Position.FIRST
  var numberOfPossiblePositions = 0

  val leftBorder = 30
  val topBorder = 30
  val rightBorder = 730
  val bottomBorder = 1430

  val downStepLength = 70

  val cells = ArrayList<Rect>()
  lateinit var currentBlock: TetrisBlock

  val validator = BlockMovementValidator()

  val painter: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    strokeWidth = 30f
    style = Paint.Style.FILL
    color = getRandomColor()
  }

  fun drawBackgroundField(canvas: Canvas) {
    val painter = Paint(Paint.ANTI_ALIAS_FLAG).apply {
      strokeWidth = 30f
      style = Paint.Style.FILL
      color = Color.LTGRAY
    }
    val rect = Rect(30, 30, 730, 1430)
    canvas.drawRect(rect, painter)
  }

  fun getBlock() = when (Random().nextInt(5) + 1) {
    1 -> OBlock()
    2 -> SBlock()
    3 -> IBlock()
    4 -> LBlock()
    5 -> TBlock()
    else -> throw Exception("Invalid choice")
  }

  fun getRandomColor() = when (Random().nextInt(5) + 1) {
    1 -> Color.RED
    2 -> Color.GREEN
    3 -> Color.BLUE
    4 -> Color.YELLOW
    5 -> Color.MAGENTA
    else -> throw Exception("Invalid choice")
  }

  fun getTestBlock(): TetrisBlock {
    return SBlock()
  }

  fun draw(canvas: Canvas) {
    cells.forEach { canvas.drawRect(it, painter)
    //Log.d("Coordinates","Left: ${it.left} | Right ${it.right} | Bottom ${it.bottom}")
    //Log.d("Block size","Number of rectangles ${cells.size}")
    }
  }

    fun stopMoving(block: List<Rect>){
        block.forEach {
            it.left = it.left
            it.top = it.top
            it.right = it.right
            it.bottom = it.bottom
        }
    }

  fun rotateBlock() {
    when (this) {
      is IBlock -> {
        this.rotate()
      }
      is LBlock -> {
        this.rotate()
      }
      is TBlock -> {
        this.rotate()
      }
      is SBlock -> {
        this.rotate()
      }
    }
  }

  fun forceDownBlock() {
    when (this) {
      is IBlock -> {
        if (this.position.equals(Position.FIRST)) {
          cells[0].bottom = bottomBorder
          cells[0].top = bottomBorder - 70
        } else {
          cells[0].bottom = bottomBorder
          cells[0].top = bottomBorder - 280
        }
      }
      is LBlock -> {
        when(this.position) {
          Position.FIRST -> {
            cells[0].bottom = bottomBorder - 70
            cells[0].top = bottomBorder - 140
            cells[1].bottom = bottomBorder
            cells[1].top = bottomBorder - 70
          }
          Position.SECOND -> {
            cells[0].bottom = bottomBorder
            cells[0].top = bottomBorder - 210
            cells[1].bottom = bottomBorder
            cells[1].top = bottomBorder - 70
          }
          Position.THIRD -> {
            cells[0].bottom = bottomBorder
            cells[0].top = bottomBorder - 70
            cells[1].bottom = bottomBorder - 70
            cells[1].top = bottomBorder - 140
          }
          Position.FOURTH -> {
            cells[0].top = bottomBorder - 210
            cells[0].bottom = bottomBorder
            cells[1].bottom = bottomBorder - 140
            cells[1].top = bottomBorder - 210
          }
        }
      }
      is TBlock -> {
        when(this.position) {
          Position.FIRST -> {
            cells[0].bottom = bottomBorder - 70
            cells[0].top = bottomBorder - 140
            cells[1].top = bottomBorder - 70
            cells[1].bottom = bottomBorder
          }
          Position.SECOND -> {
            cells[0].top = bottomBorder - 210
            cells[0].bottom = bottomBorder
            cells[1].bottom = bottomBorder - 70
            cells[1].top = bottomBorder - 140
          }
          Position.THIRD -> {
            cells[0].top = bottomBorder - 70
            cells[0].bottom = bottomBorder
            cells[1].bottom = bottomBorder - 70
            cells[1].top = bottomBorder - 140
          }
          Position.FOURTH -> {
            cells[0].top = bottomBorder - 210
            cells[0].bottom = bottomBorder
            cells[1].bottom = bottomBorder - 70
            cells[1].top = bottomBorder - 140
          }
        }
      }
      is SBlock -> {
        when(this.position) {
          Position.FIRST -> {
            cells[0].bottom = bottomBorder - 70
            cells[0].top = bottomBorder - 140
            cells[1].top = bottomBorder - 70
            cells[1].bottom = bottomBorder
          }
          Position.SECOND -> {
            cells[0].top = bottomBorder - 210
            cells[0].bottom = bottomBorder - 70
            cells[1].bottom = bottomBorder
            cells[1].top = bottomBorder - 140
          }
        }
      }
      is OBlock -> {
        cells[0].bottom = bottomBorder - 70
        cells[0].top = bottomBorder - 140
        cells[1].top = bottomBorder - 70
        cells[1].bottom = bottomBorder
      }
    }
  }

  fun move(direction: Char) = when (direction) {
    'L' -> {
      Log.d("CanMoveLeft", "${validator.canMoveLeft(cells)}")

      if (validator.canMoveLeft(cells)) {
        cells.forEach {
          it.right -= cellWidth
          it.left -= cellWidth
        }
      } else {
        // TODO nothing
      }
    }
    'R' -> {
      Log.d("CanMoveRight", "${validator.canMoveRight(cells)}")
      if (validator.canMoveRight(cells)) {
        cells.forEach {
          it.right += cellWidth
          it.left += cellWidth
        }
      } else {
        // TODO nothing
      }
    }
    'D' -> {
      Log.d("CanMoveDown", "${validator.canMoveDown(cells)}")

      if (validator.canMoveDown(cells)) {
        cells.forEach {
          it.top += downStepLength
          it.bottom += downStepLength
        }
      } else {
        // stop moving block
      }
    }
    'C' -> rotateBlock()
    'F' -> {
      // moveForceDown
      forceDownBlock()
      /*if (cells.size.equals(2)) {
        cells[0].bottom = bottomBorder - 70
        cells[0].top = bottomBorder - 140
        cells[1].bottom = bottomBorder
        cells[1].top = bottomBorder - 70
      } else {
        cells[0].bottom = bottomBorder
        cells[0].top = bottomBorder - 280
      }*/
    }
    else -> throw Exception("Invalid move, choose from 'L', 'R', 'D', 'R', 'F'")
  }



}