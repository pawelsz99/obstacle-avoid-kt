package com.pawelsznuradev.obstacleavoid.utils

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * @author Pawel Sznura 18/06/2020
 */

fun Viewport.drawGrid(renderer: ShapeRenderer, cellSize: Int = 1) {
    val oldColor = renderer.color.cpy()
    val doubleWorldWidth = worldWidth * 2
    val doubleWorldHeight = worldHeight * 2

    renderer.use {
        renderer.color = Color.WHITE




        //horizontal lines
        for (y in -doubleWorldHeight.toInt() until doubleWorldHeight.toInt() step cellSize){
            renderer.line(-doubleWorldWidth, y.toFloat(), doubleWorldWidth, y.toFloat())

        }

        //vertical lines
        for (x in -doubleWorldWidth.toInt() until doubleWorldWidth.toInt() step cellSize){
            renderer.line(x.toFloat(), -doubleWorldHeight.toFloat(), x.toFloat(), doubleWorldHeight)
        }

        renderer.color = Color.RED
        renderer.line(-doubleWorldWidth, 0f, doubleWorldWidth, 0f)
        renderer.line(0f, -doubleWorldHeight, 0f, doubleWorldHeight)

        //world bounds
        renderer.color = Color.GREEN
        renderer.line(0f,worldHeight, worldWidth, worldHeight)
        renderer.line(worldWidth, 0f, worldWidth, worldHeight)



    }

    renderer.color = oldColor

}