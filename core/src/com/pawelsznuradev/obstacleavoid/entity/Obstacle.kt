package com.pawelsznuradev.obstacleavoid.entity

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector


/**
 * @author Pawel Sznura 19/06/2020
 */
class Obstacle : GameObjectBase() {

    companion object {
        // == constants ===
        const val BOUNDS_RADIUS = 0.5f
        private const val SIZE = BOUNDS_RADIUS * 2

    }

    override val bounds: Circle = Circle(x,y, BOUNDS_RADIUS)

    private var ySpeed = 0.1f

    fun update() {
        y -= ySpeed

    }



}