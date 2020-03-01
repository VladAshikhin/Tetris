package com.om.tetris.shapes

import android.graphics.Rect
import android.util.Log
import com.om.tetris.Position
import com.om.tetris.TetrisBlock

class SBlock : TetrisBlock() {

  fun rotate() {
    val block1 = cells[0]
    val block2 = cells[1]
    when(this.position) {
      Position.FIRST -> {
        block1.left = block1.left
        block1.top = block1.top - 70
        block1.right = block1.right - 70
        block1.bottom = block1.bottom

        block2.left = block2.left + 140
        block2.top = block2.top - 70
        block2.right = block2.right + 70
        block2.bottom = block2.bottom

        this.position = Position.SECOND
      }
      Position.SECOND -> {
        if(block1.left.equals(leftBorder)) {
          block1.left = block1.left + 70
          block1.top = block1.top + 70
          block1.right = block1.right + 140
          block1.bottom = block1.bottom

          block2.left = block2.left - 70
          block2.top = block2.top + 70
          block2.right = block2.right
          block2.bottom = block2.bottom
        } else {
          block1.left = block1.left
          block1.top = block1.top + 70
          block1.right = block1.right + 70
          block1.bottom = block1.bottom

          block2.left = block2.left - 140
          block2.top = block2.top + 70
          block2.right = block2.right - 70
          block2.bottom = block2.bottom
        }

        this.position = Position.FIRST
      }
      else -> {
        Log.d("Rotation", "Can't rotate block.")
      }
    }
  }

  init {
    numberOfPossiblePositions = 2
    cells.add(Rect(spacing + cellWidth * 5, spacing, spacing + cellWidth * 7, cellHeight + spacing))
    cells.add(Rect(spacing + cellWidth * 4, spacing + cellHeight, spacing + cellWidth * 6, cellHeight * 2 + spacing))
  }
}