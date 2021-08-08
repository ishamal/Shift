package com.temper.myapplication.exceptions

import java.io.IOException

class NoConnectivityException(message: String = "No internet connectivity!") :
    IOException(message)