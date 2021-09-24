package com.proceedto15.wb.database.repositories

import com.proceedto15.wb.database.daos.DiplomadoDAO
import com.proceedto15.wb.database.daos.ImpartidorDAO

class DiplomadoRepository (
    private val ImpartidorDAO: ImpartidorDAO,
    private val DiplomadoDAO: DiplomadoDAO) {

}