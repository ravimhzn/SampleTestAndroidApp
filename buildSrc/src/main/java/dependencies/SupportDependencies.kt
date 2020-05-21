package dependencies

import dependencies.Versions.recyclerview_version

object SupportDependencies {

    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val material_design = "com.google.android.material:material:${Versions.material_design}"
    val cardLayout = "androidx.cardview:cardview:${Versions.cardView}"
    val swipe_refresh_layout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_refresh_layout}"
    val recyclerView = "androidx.recyclerview:recyclerview:$recyclerview_version"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
}
