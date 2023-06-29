<template>
    <v-app>
        <v-app-bar
                app
                color="primary"
                dark
        >
            <v-spacer></v-spacer>
            <span v-if="isAuth" class="mx-5">
                {{this.$store.state.account.userName}}
            </span>
            <v-btn
                    color="error"
                    v-if="isAuth"
                    @click="logout"
            >Выйти
            </v-btn>
        </v-app-bar>
        <v-main>
            <template v-if="isAuth">
                <MainBoard/>
            </template>
            <template v-else>
                <AuthPage/>
            </template>
        </v-main>
    </v-app>
</template>

<script>
import MainBoard from './components/MainBoard.vue';
import AuthPage from "@/components/AuthPage.vue";
import {mapGetters, mapMutations} from "vuex";

export default {
    name: 'App',

    components: {
        MainBoard, AuthPage
    },

    data: () => ({}),
    computed: {
        ...mapGetters(['isAuth'])

    },
    methods: {
        ...mapMutations(['setAuth']),
        logout(){
            this.setAuth(false)
        }
    }
};
</script>
