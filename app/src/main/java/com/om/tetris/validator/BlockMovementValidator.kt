package com.om.tetris.validator

import android.graphics.Rect
import android.util.Log
import com.om.tetris.TetrisBlock

class BlockMovementValidator {

    val leftBorder = 30
    val topBorder = 30
    val rightBorder = 730
    val bottomBorder = 1430

    fun canMoveLeft(cells: List<Rect>): Boolean {
        return cells.none { it.left <= leftBorder }
    }

    fun canMoveRight(cells: List<Rect>): Boolean {
        return cells.none { it.right == rightBorder }
    }

    fun canMoveDown(cells: List<Rect>): Boolean {
        return cells.none { it.bottom >= bottomBorder }
    }

    fun getLowestBlock(block: TetrisBlock): Rect {
        if(block.cells.size.equals(1)) {
            return block.cells[0]
        } else {
            if (block.cells[0].bottom > block.cells[1].bottom) {
                return block.cells[0]
            } else {
                return block.cells[1]
            }
        }
    }

    fun preventPlacementBlock(block: TetrisBlock, staticBlocks: MutableList<TetrisBlock>): Rect? {
        val lowestBlock = getLowestBlock(block)
        Log.d("Low block bottom", "${lowestBlock.bottom}")


        // get range between left and right of the block, i.e. 100 <-> 380
        // if there is static block which has coordinates between 101 and 379
        // get this static block's top and put block's bottom to it

        val left = lowestBlock.left
        val right = lowestBlock.right
        Log.d("block", "left $left + right $right")
        for(x in left..right) {
            staticBlocks.forEach { static -> static.cells.forEach { rect ->
                run {
                    for (y in rect.left..rect.right) {
                        Log.d("Static", "x $x + y $y")
                        if (x == y && lowestBlock.bottom == rect.top) return rect
                    }
                }
            } }
        }
        return null
    }

}