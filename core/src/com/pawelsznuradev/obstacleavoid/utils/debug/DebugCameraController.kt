package com.pawelsznuradev.obstacleavoid.utils.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.pawelsznuradev.obstacleavoid.utils.isKeyPressed
import com.pawelsznuradev.obstacleavoid.utils.logger

/**
 * @author Pawel Sznura 18/06/2020
 */
class DebugCameraController {

    companion object {
        @JvmStatic
        private val log = logger<DebugCameraController>()

        private const val DEFAULT_LEFT_KEY = Input.Keys.A
        private const val DEFAULT_RIGHT_KEY = Input.Keys.D
        private const val DEFAULT_DOWN_KEY = Input.Keys.S
        private const val DEFAULT_UP_KEY = Input.Keys.W
        private const val DEFAULT_MOVE_SPEED = 20f
    }

    private val position = Vector2()
    private val startPosition = Vector2()

    fun setStartPosotion(x:Float, y:Float){
        startPosition.set(x,y)
        setPosition(x,y)
    }


    fun applyTo(camera: OrthographicCamera){
        camera.position.set(position, 0f)
        camera.update()
    }

    fun handleDebugInput(){
        val delta = Gdx.graphics.deltaTime
        val moveSpeed = DEFAULT_MOVE_SPEED * delta

        when{ //move controls
            DEFAULT_LEFT_KEY.isKeyPressed() -> moveLeft(moveSpeed)
            DEFAULT_RIGHT_KEY.isKeyPressed() -> moveRight(moveSpeed)
            DEFAULT_DOWN_KEY.isKeyPressed() -> moveDown(moveSpeed)
            DEFAULT_UP_KEY.isKeyPressed() -> moveUp(moveSpeed)



        }

    }

    private fun moveCamera(xSpeed: Float, ySpeed:Float) = setPosition(position.x + xSpeed, position.y + ySpeed)

    private fun setPosition(x: Float, y: Float) = position.set(x,y)


    private fun moveUp(moveSpeed: Float) {
        moveCamera(0f,moveSpeed)

    }

    private fun moveDown(moveSpeed: Float) {
        moveCamera(0f,-moveSpeed)

    }

    private fun moveRight(moveSpeed: Float) {
        moveCamera(moveSpeed, 0f)
    }

    private fun moveLeft(moveSpeed: Float) {
        moveCamera(-moveSpeed, 0f)
    }

}