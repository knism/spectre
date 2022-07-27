package org.knism.spectre.execptions

/**
 * exception thrown when trying to open a window in non-headless
 * application, should not be caught
 *
 * @constructor description message of the exception
 */
class WindowCouldNotBeOpenedException(message: String) : Exception(message)