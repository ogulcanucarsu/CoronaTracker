package org.ucarsu.coronaexample.app.presentation.extension

import org.ucarsu.coronaexample.app.presentation.constants.Constants

/*
 * Checks if the given Int is a Resource Id.
 */
fun Int?.isValidResource() = this != Constants.NO_RES