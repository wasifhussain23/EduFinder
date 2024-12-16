package com.wasifS3301912.edufinder

data class UniversityDetails(
    val universityName: String = "",
    val universityLocation: String = "",
    val universityDescription: String = "",
    val universityImage: Int=0,
    val universityAdmissionDate: String = "",
    val type: String = "",// can be School,or College or University
    val programsOffered: String = "",
    val faculty: String = "", //James Roy - ComputerScience
    val facilities: String = "",
    var featureList: List<FeaturesData> = emptyList(),
)

data class FeaturesData(
    val text1: String = "",
    val text2: String = "",
    val text3: String = "",
    val text4: String = "",
    val text5: String = "",
)
