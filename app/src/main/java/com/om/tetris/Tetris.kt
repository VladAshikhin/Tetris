package com.om.tetris

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.View
import com.om.tetris.validator.BlockMovementValidator

class Tetris(context: Context) : View(context) {

    val tetrisBlock = TetrisBlock()

    var randomBlock = tetrisBlock.getBlock()
    //var randomBlock = TetrisBlock().getTestBlock()
    val coordinates: MutableList<Int> = mutableListOf()
    val painter = TetrisBlock().painter

    val blockList: MutableList<TetrisBlock> = mutableListOf()

    val validator = BlockMovementValidator()

    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)

        tetrisBlock.drawBackgroundField(canvas)
        randomBlock.draw(canvas)

        tetrisBlock.currentBlock = randomBlock

        val blockThatPreventsMove = validator.preventPlacementBlock(randomBlock, blockList)
        if(blockThatPreventsMove != null) {
            randomBlock.cells[0].bottom = blockThatPreventsMove.top
            randomBlock.cells[0].top = blockThatPreventsMove.top + 100
        }

        // if block reached bottom
        if (!validator.canMoveDown(randomBlock.cells)) {

            blockList.add(randomBlock)

            randomBlock = tetrisBlock.getBlock()
            //randomBlock = TetrisBlock().getTestBlock()
            randomBlock.draw(canvas)

        }
        if (blockList.isNotEmpty()) blockList.forEach { it.draw(canvas) }

        Log.d("Blocklist size", "${blockList.size}")
    }

    fun copyBlock(cells: List<Rect>, canvas: Canvas) {
        val copy = TetrisBlock()

        if (cells.size == 1) {
            val block1 = Rect(cells[0].left + 120, cells[0].top, cells[0].right + 120, cells[0].bottom)
            canvas.drawRect(block1, painter)
            //copy.cells.add(block1)
            Log.d("Bottom block1 ", "${block1.bottom}")
        } else {
            val block1 = Rect(cells[0].left, cells[0].top, cells[0].right, cells[0].bottom)
            val block2 = Rect(cells[1].left, cells[1].top, cells[1].right, cells[1].bottom)
            canvas.drawRect(block1, painter)
            canvas.drawRect(block2, painter)
            Log.d("Bottom block2 ", "${block2.bottom}")
            //copy.cells.addAll(listOf(block1, block2))
        }
    }

    fun setStatic(block: TetrisBlock) {
        TetrisBlock().stopMoving(block.cells)
    }

    fun setCurrentCoordinates(block: TetrisBlock) {
        coordinates.clear()
        block.cells.forEach {
            coordinates.addAll(listOf(it.left, it.top, it.right, it.bottom))
        }
        Log.d("COORDINATES", "Random block coords $coordinates")
    }

    fun loop() {
        render()
    }

    fun render() {
        //if (TetrisBlock().canMoveDown(randomBlock.cells)) {
            randomBlock.move('D')
        //}

        invalidate()
    }

    fun moveRight() {
        randomBlock.move('R')
        invalidate()
    }

    fun moveLeft() {
        randomBlock.move('L')
        invalidate()
    }

    fun moveDown() {
        randomBlock.move('D')
        invalidate()
    }

    fun rotate() {
        randomBlock.move('C')
        invalidate()
    }

    fun moveForceDown() {
        randomBlock.move('F')
        invalidate()
    }

}