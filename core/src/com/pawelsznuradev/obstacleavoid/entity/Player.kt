package com.pawelsznuradev.obstacleavoid.entity

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.pawelsznuradev.obstacleavoid.utils.circle
import com.pawelsznuradev.obstacleavoid.utils.isKeyPressed


/**
 * @author Pawel Sznura 18/06/2020
 */
class Player {
    companion object{
        // == constants ===
        private const val BOUNDS_RADIUS = 0.4f
        private const val SIZE = BOUNDS_RADIUS * 2
        private const val MAX_X_SPEED = 0.1f

    }
    // == properties
    var x = 0f

    var y = 0f

    val bounce: Circle

    // == init ==
    init {
        bounce = Circle(x,y, BOUNDS_RADIUS)
    }

    // == public functions ==
    fun setPosition(x:Float, y:Float){
        this.x = x
        this.y = y

        updateBounds()
    }

    fun update(){
        var xSpeed = 0f

        when{
            Input.Keys.RIGHT.isKeyPressed() -> xSpeed = MAX_X_SPEED
            Input.Keys.LEFT.isKeyPressed() -> xSpeed = -MAX_X_SPEED
        }       //TODO player cannot have instantly full speed

        x += xSpeed
        updateBounds()

    }

    fun drawDebug(renderer: ShapeRenderer) = renderer.circle(bounce)

    // == private functions ==
    private fun updateBounds() = bounce.setPosition(x,y)

}