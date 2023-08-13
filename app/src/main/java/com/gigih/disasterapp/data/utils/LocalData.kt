package com.gigih.disasterapp.data.utils

import com.gigih.disasterapp.data.model.DisasterType
import com.gigih.disasterapp.data.model.LocationType

object LocalData {
    fun getSupportedLocationType(): List<LocationType> {
        val locations = ArrayList<LocationType>().apply {
            add(
                LocationType("Aceh", "ID-AC")
            )
            add(
                LocationType("Bali", "ID-BA")
            )
            add(
                LocationType("Kep Bangka Belitung", "ID-BB")
            )
            add(
                LocationType("Banten", "ID-JT")
            )
            add(
                LocationType("Bengkulu", "ID-BE")
            )
            add(
                LocationType("Jawa Tengah", "ID-JT")
            )
            add(
                LocationType("Kalimantan Tengah", "ID-KT")
            )
            add(
                LocationType("Sulawesi Tengah", "ID-ST")
            )
            add(
                LocationType("Jawa Timur", "ID-JI")
            )
            add(
                LocationType("Kalimantan Timur", "ID-TI")
            )
            add(
                LocationType("Nusa Tenggara Timur", "ID-NT")
            )
            add(
                LocationType("Gorontalo", "ID-GO")
            )
            add(
                LocationType("DKI Jakarta", "ID-JK")
            )
            add(
                LocationType("Jambi", "ID-JA")
            )
            add(
                LocationType("Lampung", "ID-LA")
            )
            add(
                LocationType("Maluku", "ID-MA")
            )
            add(
                LocationType("Kalimantan Utara", "ID-KU")
            )
            add(
                LocationType("Maluku Utara", "ID-MU")
            )
            add(
                LocationType("Sulawesi Utara", "ID-SA")
            )
            add(
                LocationType("Sumatera Utara", "ID-SU")
            )
            add(
                LocationType("Papua", "ID-PA")
            )
            add(
                LocationType("Riau", "ID-RI")
            )
            add(
                LocationType("Kepulauan Riau", "ID-KR")
            )
            add(
                LocationType("Sulawesi Tenggara", "ID-SG")
            )
            add(
                LocationType("Kalimantan Selatan", "ID-KS")
            )
            add(
                LocationType("Sulawesi Selatan", "ID-SN")
            )
            add(
                LocationType("Sumatera Selatan", "ID-SS")
            )
            add(
                LocationType("DI Yogyakarta", "ID-YO")
            )
            add(
                LocationType("Jawa Barat", "ID-JB")
            )
            add(
                LocationType("Kalimantan Barat", "ID-KB")
            )
            add(
                LocationType("Nusa Tenggara Barat", "ID-NB")
            )
            add(
                LocationType("Papua Barat", "ID-PB")
            )
            add(
                LocationType("Sulawesi Barat", "ID-SR")
            )
            add(
                LocationType("Sumatera Barat", "ID-SB")
            )
        }
        return locations
    }

    fun getDisasterTypes(): List<DisasterType> {
        val disasterTypes = ArrayList<DisasterType>().apply {
            add(
                DisasterType("Banjir", "flood")
            )
            add(
                DisasterType("Gempa Bumi", "earthquake")
            )
            add(
                DisasterType("Kebakaran", "fire")
            )
            add(
                DisasterType("Kabut", "haze")
            )
            add(
                DisasterType("Angin Kencang", "wind")
            )
            add(
                DisasterType("Gunung Api", "volcano")
            )
        }
        return disasterTypes
    }
}