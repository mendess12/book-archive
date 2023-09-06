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
![login](https://github.com/mendess12/book-archive/assets/76566952/d6f0f9a3-6a40-4f26-aabe-f196519e71b2) ![register](https://github.com/mendess12/book-archive/assets/76566952/3f8c5513-0deb-45dd-88de-d66d9490e1ed) ![forgot-password](https://github.com/mendess12/book-archive/assets/76566952/f0bde6a5-5bc1-4976-beeb-37b69b7ec031)  ![change-password](https://github.com/mendess12/book-archive/assets/76566952/7a230087-f216-417d-83be-86cd2f75c91d)

### Home
![home](https://github.com/mendess12/book-archive/assets/76566952/90e557ad-043c-4714-8d73-70ad009d7bfc) ![search](https://github.com/mendess12/book-archive/assets/76566952/ad6098af-8470-409d-b680-63a55cf30eb6) ![book-detail](https://github.com/mendess12/book-archive/assets/76566952/b4791fd9-ea88-42a2-bdbf-6a848b53d994) ![add-book](https://github.com/mendess12/book-archive/assets/76566952/0a74fe09-5a09-4b24-b2b5-21aef8f20d9a)

### Suggestion 
![suggestion](https://github.com/mendess12/book-archive/assets/76566952/cf99520e-fb44-4227-a192-bfce9674bd38)





