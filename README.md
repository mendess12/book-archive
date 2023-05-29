# book-archive

### Konu
<pre>Firebase Authentication ve Firestore database'lerini kullanıp <br>kullanıcıların uygulamaya kayıt olarak kitaplığındaki kitapları kayıt etme işlemleri</pre>
<hr>

### Hedef
1- Navigation component ile fragment kullanımı<br>
2- Login,register,forgot password ve change password işlemleri için firebase authentication kullanımı<br>
3- Kitap ekleme ve eklenen kitapları listeleme işlemleri için firebase firestore kullanımı<br>
4- Kitap listesindeki item'lara tıklanınca o kitapın özelliklerini göstermek için expandable özelliği kullanımı
<hr>

### Teknolojiler
- Database işlemleri için firebase<br>
- Veri akışını yönetmek için LiveData, arayüz durumunun yönetimi için UIState<br>
- Fragmentlar arası geçiş ve veri göndermek için Jetpack Navigation<br>
- Listeleme işlemleri için RecyclerView<br>
<hr>

### Dependencies 
<pre>
- Plugin 
  id 'com.google.gms.google-services'
  id 'androidx.navigation.safeargs.kotlin'
 
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
    
- build.gradle(:project)
  def nav_version = "2.5.3"
  classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
  classpath 'com.google.gms:google-services:4.3.15'
</pre>
<hr>

### Kaynaklar 
* [Firebase Authentication](https://firebase.google.com/docs/auth/android/firebaseui?authuser=1)
* [Firebase Firestore](https://firebase.google.com/docs/firestore/quickstart?hl=en&authuser=1)
* [LiveData Overview](https://developer.android.com/topic/libraries/architecture/livedata)
* [Fragment & Fragment View lifecycle](https://developer.android.com/guide/fragments/lifecycle)
* [Activity lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
* [ViewModel and ViewModel lifecycle](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Learn Jetpack Navigation - Codelab](https://developer.android.com/codelabs/android-navigation#0)
* [Expandable](https://medium.com/swlh/expandable-recycler-view-in-kotlin-41bca0bc80cf)
<hr>

### UI Tasarımları


