package com.om.tetris.shapes

import android.graphics.Rect
import com.om.tetris.TetrisBlock

class OBlock : TetrisBlock() {

  init {
    numberOfPossiblePositions = 0
    cells.add(Rect(spacing + cellWidth * 4, spacing, spacing + cellWidth * 6, cellHeight + spacing))
    cells.add(Rect(spacing + cellWidth * 4, cellHeight + spacing, spacing + cellWidth * 6, cellHeight * 2 + spacing))

  }
}