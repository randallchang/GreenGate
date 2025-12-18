import {type App,
        createApp} from 'vue';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import AppComponent from './App.vue';
import router from './router';

const app: App<Element> = createApp(AppComponent)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
