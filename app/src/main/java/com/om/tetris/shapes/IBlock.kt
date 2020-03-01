package com.om.tetris.shapes

import android.graphics.Rect
import android.util.Log
import com.om.tetris.Position
import com.om.tetris.TetrisBlock

class IBlock : TetrisBlock() {

    fun rotate() {
        val block = cells[0]
        when (this.position) {
            Position.FIRST -> {

                if (!block.top.equals(topBorder)) {
                    // make 2nd
                    block.left = block.left + 140
                    block.top = block.top - 70
                    block.right = block.right - 70
                    block.bottom = block.bottom + 140

                    this.position = Position.SECOND
                }
            }
            Position.SECOND -> {
                if (!(block.left.equals(leftBorder) ||
                                block.left.equals(leftBorder + 70) || block.right.equals(rightBorder))) {
                    //make 1st
                    block.left = block.left - 140
                    block.top = block.top + 70
                    block.right = block.right + 70
                    block.bottom = block.bottom - 140
                    this.position = Position.FIRST
                }
            }
            else -> {
                Log.d("Rotation", "Can't rotate block.")
            }
        }
    }

    init {
        numberOfPossiblePositions = 2
        cells.add(Rect(spacing + cellWidth * 4, spacing, spacing + cellWidth * 8, spacing + cellHeight))
    }
}