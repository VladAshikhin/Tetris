package com.om.tetris.shapes

import android.graphics.Rect
import com.om.tetris.TetrisBlock

class TestBlock : TetrisBlock() {

    var top: Int = 0
    var bottom: Int = 0

    init {
        cells.add(Rect(640, 240, 840, 340))
        cells.add(Rect(640, 340, 840, 440))

    }
}