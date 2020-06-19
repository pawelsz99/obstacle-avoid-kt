package com.pawelsznuradev.obstacleavoid.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector
import com.pawelsznuradev.obstacleavoid.utils.circle

/**
 * @author Pawel Sznura 19/06/2020
 */
abstract class GameObjectBase {
    var x = 0f
        set(value) {
            field = value
            updateBounds()
        }
    var y = 0f
        set(value) {
            field = value
            updateBounds()
        }
    abstract val bounds :Circle

    fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    private fun updateBounds() = bounds.setPosition(x, y)
    fun drawDebug(renderer: ShapeRenderer) = renderer.circle(bounds)

    fun isCollidingWith(gameObject: GameObjectBase): Boolean = Intersector.overlaps(gameObject.bounds, bounds)

}