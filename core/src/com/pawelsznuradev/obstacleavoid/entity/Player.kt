package com.pawelsznuradev.obstacleavoid.entity

import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Circle
import com.pawelsznuradev.obstacleavoid.utils.isKeyPressed


/**
 * @author Pawel Sznura 18/06/2020
 */
class Player : GameObjectBase() {
    companion object {
        // == constants ===
        private const val BOUNDS_RADIUS = 0.4f
        private const val MAX_X_SPEED = 0.1f

        const val HALF_SIZE = BOUNDS_RADIUS / 2
    }

    override val bounds: Circle = Circle(x,y, BOUNDS_RADIUS)


    fun update() {
        var xSpeed = 0f

        when {
            Input.Keys.RIGHT.isKeyPressed() -> xSpeed = MAX_X_SPEED
            Input.Keys.LEFT.isKeyPressed() -> xSpeed = -MAX_X_SPEED
        }       //TODO player cannot have instantly full speed

        x += xSpeed

    }


}