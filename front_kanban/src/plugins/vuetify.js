import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#033047',
                secondary: '#5c677d',
                accent: '#8c9eff',
                error: '#b71c1c',
                success: '#3ab795',
                main_back: '#f4f3ee'
            },
        },
    },
});
