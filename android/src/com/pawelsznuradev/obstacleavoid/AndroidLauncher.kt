package com.pawelsznuradev.obstacleavoid

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

/**
 * @author Pawel Sznura 16/06/2020
 */
class AndroidLauncher :AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize(ObstacleAvoidGame(), AndroidApplicationConfiguration())

    }
}