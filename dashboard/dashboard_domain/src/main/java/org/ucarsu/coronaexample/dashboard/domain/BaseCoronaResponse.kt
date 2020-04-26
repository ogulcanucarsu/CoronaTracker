package org.ucarsu.coronaexample.dashboard.domain

abstract class BaseCoronaResponse {
    abstract val totalCases: String?
    abstract val newCases: String?
    abstract val totalDeaths: String?
    abstract val newDeaths: String?
    abstract val totalRecovered: String?
    abstract val activeCases: String
}