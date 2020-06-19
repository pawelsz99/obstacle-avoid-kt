package com.pawelsznuradev.obstacleavoid.utils.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.pawelsznuradev.obstacleavoid.utils.logger

/**
 * @author Pawel Sznura 18/06/2020
 */
class DebugCameraController {

    companion object {
        @JvmStatic
        private val log = logger<DebugCameraController>()

    }

    private val position = Vector2()
    private val startPosition = Vector2()
    private val config = DebugCameraConfig()


    private var zoom = 1f
        set(value) {
            field = MathUtils.clamp(value, config.maxZoomIn, config.maxZoomOut)
        }

    init {
        log.debug("$config")

    }



    fun setStartPosotion(x: Float, y: Float) {
        startPosition.set(x, y)
        setPosition(x, y)
    }


    fun applyTo(camera: OrthographicCamera) {
        camera.position.set(position, 0f)
        camera.zoom = zoom
        camera.update()

    }

    fun handleDebugInput() {
        val delta = Gdx.graphics.deltaTime
        val moveSpeed = config.moveSpeed * delta
        val zoomSpeed = config.zoomSpeed * delta

        when { //move controls
            config.isLeftPressed() -> moveLeft(moveSpeed)
            config.isRightPressed() -> moveRight(moveSpeed)
            config.isDownPressed() -> moveDown(moveSpeed)
            config.isUpPressed() -> moveUp(moveSpeed)
            //zoom
            config.isZoomInPressed() -> zoomIn(zoomSpeed)
            config.isZoomOutPressed() -> zoomOut(zoomSpeed)
            config.isResetPressed() -> reset()
            //log
            config.isLogPressed() -> log.debug("position= $position, zoom = $zoom ")

        }


    }
    private fun reset() {
        position.set(startPosition)
        zoom = 1f
    }

    private fun zoomIn(zoomSpeed: Float) {
        zoom += zoomSpeed
    }

    private fun zoomOut(zoomSpeed: Float) {
        zoom -= zoomSpeed
    }

    private fun moveCamera(xSpeed: Float, ySpeed: Float) = setPosition(position.x + xSpeed, position.y + ySpeed)

    private fun setPosition(x: Float, y: Float) = position.set(x, y)


    private fun moveUp(moveSpeed: Float) {
        moveCamera(0f, moveSpeed)

    }

    private fun moveDown(moveSpeed: Float) {
        moveCamera(0f, -moveSpeed)

    }

    private fun moveRight(moveSpeed: Float) {
        moveCamera(moveSpeed, 0f)
    }

    private fun moveLeft(moveSpeed: Float) {
        moveCamera(-moveSpeed, 0f)
    }

}