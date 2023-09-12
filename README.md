[suggesiton-video.webm](https://github.com/mendess12/book-archive/assets/76566952/2db1f232-28e6-44ad-b323-183b201c75c5)# book-archive

### Konu
<pre>Firebase Authentication ve Firestore database'lerini kullanıp <br>kullanıcıların uygulamaya kayıt olarak kitaplığındaki kitapları kayıt etme işlemleri</pre>
<hr>

### Hedef
1- Navigation component ile fragment kullanımı<br>
2- Login,register,forgot password ve change password işlemleri için firebase authentication kullanımı<br>
3- Kitap ekleme ve eklenen kitapları listeleme işlemleri için firebase firestore kullanımı<br>
4- Kitap listesindeki item'lara tıklanınca o kitapın detay özelliklerini göstermek için detay ekranına gitme işlemi<br>
5- Kitap listesinde kitabın ismine göre arama yapma işlemi<br>
6- Firebase'de bulunan en çok okunan kitapların listesini kullanıcılara önerme işlemi<br>
<hr>

### Teknolojiler
- Kullanıcı işlemleri için Firebase Authentication,
- Database işlemleri için Firebase Firestore,
- Tasrım widget'larına erişmek için ViewBinding,
- Veri akışını yönetmek için LiveData, arayüz durumunun yönetimi için UIState,
- Fragmentlar arası geçiş ve veri göndermek için Jetpack Navigation component,
- Model olarak MVVM,
- Repository ve ViewModel bağlantısı için Use Cases,
- Asenkron işlemler için Coroutine
- Bağımlılık yönetimi için Dagger - Hilt
- Veri aktarımı için serializable kullandım.
<hr>

### Dependencies 
<pre>
- Plugin 
  id 'com.google.gms.google-services'
  id 'androidx.navigation.safeargs.kotlin'
  id 'dagger.hilt.android.plugin'
  id 'kotlin-kapt'
 
- build.gradle(:app)
  //firebase bom
  implementation platform('com.google.firebase:firebase-bom:32.0.0')

  //firebase analytics
  implementation 'com.google.firebase:firebase-analytics-ktx'

  //firebase auth
  implementation 'com.google.firebase:firebase-auth-ktx'

  //firebase firestore
  implementation 'com.google.firebase:firebase-firestore-ktx'

  //recyclerview
  implementation("androidx.recyclerview:recyclerview:1.3.0")

  //mvvm
  implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
  implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.6.1"

  //navigation component
  implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
  implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

  //dagger - hilt
  implementation "com.google.dagger:hilt-android:2.44"
  kapt "com.google.dagger:hilt-compiler:2.44"

  //coroutines
  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4'

- build.gradle(:project)
  def nav_version = "2.5.3"
  classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
  classpath 'com.google.gms:google-services:4.3.15'
  classpath 'com.google.dagger:hilt-android-gradle-plugin:2.44'
</pre>
<hr>

### Kaynaklar 
* [Firebase Authentication](https://firebase.google.com/docs/auth/android/firebaseui?authuser=1)
* [Firebase Firestore](https://firebase.google.com/docs/firestore/quickstart?hl=en&authuser=1)
* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Use Cases](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
* [RecyclerView](https://www.tutorialkart.com/kotlin-android/kotlin-android-recyclerview/#gsc.tab=0)
* [LiveData Overview](https://developer.android.com/topic/libraries/architecture/livedata)
* [Fragment & Fragment View lifecycle](https://developer.android.com/guide/fragments/lifecycle)
* [Activity lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
* [ViewModel and ViewModel lifecycle](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Learn Jetpack Navigation - Codelab](https://developer.android.com/codelabs/android-navigation#0)
* [Filterable](https://www.tutorialsbuzz.com/2020/09/android-recyclerView-data-list-filterable-kotlin.html)
<hr>

## UI Tasarımları
### Auth
![login](https://github.com/mendess12/book-archive/assets/76566952/a44b1642-f4fa-4a7e-aec8-08e6be98ce2b) ![register](https://github.com/mendess12/book-archive/assets/76566952/f0fc7b6e-95b3-4474-bfe0-18eed42ce8e6) ![forgot-password](https://github.com/mendess12/book-archive/assets/76566952/605493dc-878d-44bb-b4d3-7043c7747018) ![change-password](https://github.com/mendess12/book-archive/assets/76566952/dcde3b5d-59eb-40bf-b294-d9196e9ff410)

### Home
![home](https://github.com/mendess12/book-archive/assets/76566952/6a1ad1f8-4dc1-4e3e-8608-51b6bf217c87) ![add-book](https://github.com/mendess12/book-archive/assets/76566952/50db9058-889d-4711-982e-f1af632855f8) ![book-detail](https://github.com/mendess12/book-archive/assets/76566952/fb375a72-511a-4379-b315-f0626c5c9ecf) ![search](https://github.com/mendess12/book-archive/assets/76566952/8650581d-f8ec-4849-86b2-39fa0a8aad3f)

### Suggestion 
![suggestion](https://github.com/mendess12/book-archive/assets/76566952/75479573-81fc-4c61-9bba-5db2ffdf6f96)






