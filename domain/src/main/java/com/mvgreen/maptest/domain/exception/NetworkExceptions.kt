package com.mvgreen.maptest.domain.exception

import java.io.IOException

class GeocodeGeneralException : IOException()

class GeocodeZeroResultsException : IOException()

class GeocodeLimitException : IOException()