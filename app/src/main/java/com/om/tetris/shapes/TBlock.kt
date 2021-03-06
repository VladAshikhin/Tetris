package com.om.tetris.shapes

import android.graphics.Rect
import com.om.tetris.Position
import com.om.tetris.TetrisBlock

class TBlock : TetrisBlock() {

    fun rotate() {
        val block1 = cells[0]
        val block2 = cells[1]
        when (this.position) {
            Position.FIRST -> {
                // make 2nd
                block1.left = block1.left + 70
                block1.top = block1.top - 70
                block1.right = block1.right - 70
                block1.bottom = block1.bottom + 70

                block2.left = block2.left + 70
                block2.top = block2.top - 70
                block2.right = block2.right + 70
                block2.bottom = block2.bottom - 70

                this.position = Position.SECOND
            }
            Position.SECOND -> {
                //make 3rd
                if (block1.left.equals(leftBorder)) {
                    block1.top = block1.top + 70
                    block1.right = block1.right + 140
                    block1.bottom = block1.bottom - 70

                    block2.top = block2.top - 70
                    block2.bottom = block2.bottom - 70
                } else {
                    block1.left = block1.left - 70
                    block1.top = block1.top + 70
                    block1.right = block1.right + 70
                    block1.bottom = block1.bottom - 70

                    block2.left = block2.left - 70
                    block2.top = block2.top - 70
                    block2.right = block2.right - 70
                    block2.bottom = block2.bottom - 70
                }
                this.position = Position.THIRD
            }
            Position.THIRD -> {
                //make 4th
                block1.left = block1.left + 70
                block1.top = block1.top - 70
                block1.right = block1.right - 70
                block1.bottom = block1.bottom + 70

                block2.left = block2.left - 70
                block2.top = block2.top + 70
                block2.right = block2.right - 70
                block2.bottom = block2.bottom + 70

                this.position = Position.FOURTH
            }
            Position.FOURTH -> {
                // make 1st
                if (block1.right.equals(rightBorder)) {
                    block1.left = block1.left - 140
                    block1.top = block1.top + 70
                    block1.bottom = block1.bottom - 70

                    block2.top = block2.top + 70
                    block2.bottom = block2.bottom + 70
                } else {
                    block1.left = block1.left - 70
                    block1.top = block1.top + 70
                    block1.right = block1.right + 70
                    block1.bottom = block1.bottom - 70

                    block2.left = block2.left + 70
                    block2.top = block2.top + 70
                    block2.right = block2.right + 70
                    block2.bottom = block2.bottom + 70
                }
                this.position = Position.FIRST
            }
        }
    }

    init {
        numberOfPossiblePositions = 4
        cells.add(Rect(spacing + cellWidth * 4, spacing, spacing + cellWidth * 7, spacing + cellHeight))
        cells.add(Rect(spacing + cellWidth * 5, spacing + cellHeight, spacing + cellWidth * 6, spacing + cellHeight * 2))
    }
}