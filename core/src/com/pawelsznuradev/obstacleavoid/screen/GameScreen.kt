package com.pawelsznuradev.obstacleavoid.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pawelsznuradev.obstacleavoid.utils.clearScreen
import com.pawelsznuradev.obstacleavoid.utils.toInternalFile
import com.pawelsznuradev.obstacleavoid.utils.use

/**
 * @author Pawel Sznura 16/06/2020
 */
class GameScreen : Screen {

    private lateinit var batch: SpriteBatch
    private lateinit var img: Texture


    override fun hide() {
        dispose()
    }

    override fun show() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg".toInternalFile())
    }

    override fun render(delta: Float) {
        clearScreen()

        batch.use {
            batch.draw(img, 0f, 0f)
        }

    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
    }
}