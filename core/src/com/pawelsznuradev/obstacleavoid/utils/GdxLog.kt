package com.pawelsznuradev.obstacleavoid.utils

import com.badlogic.gdx.utils.Logger

/**
 * @author Pawel Sznura 07/06/2020
 */

//fun <T : Any> logger(clazz: Class<T>): Logger = Logger(clazz.simpleName, Logger.DEBUG)

inline fun <reified T : Any> logger(): Logger = Logger(T::class.java.simpleName, Logger.DEBUG)