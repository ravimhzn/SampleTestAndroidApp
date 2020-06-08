package dependencies

import dependencies.Versions.glide_version
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
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    /**
     *     def glide_version = "4.9.0"
    implementation "com.github.bumptech.glide:glide:$glide_version"
    kapt "com.github.bumptech.glide:compiler:$glide_version"
     */

}
