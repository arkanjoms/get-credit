import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import AnalysisList from './components/analyse/AnalysisList';
import AnalyseEdit from './components/analyse/AnalyseEdit';

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/analysis',
            name: 'analysis',
            component: AnalysisList
        },
        {
            path: '/analyse/:cpf',
            name: 'analyseEdit',
            component: AnalyseEdit
        },
        {
            path: '/analyse',
            name: 'analyseNew',
            component: AnalyseEdit
        }
    ]
})
