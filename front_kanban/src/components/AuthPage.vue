<template>

    <v-card
            class="mx-auto mt-5"
            max-width="400"
            @keyup.enter="login"
    >

        <v-card-title>Авторизация</v-card-title>
        <v-card-text>
            <v-text-field label="Логин"
                          v-model="userName"
            ></v-text-field>
            <v-text-field label="Пароль"
                          type="password"
                          v-model="password"
            ></v-text-field>
        </v-card-text>
        <v-card-actions>
            <v-btn
                    @click="login"
            >Логин
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import {mapMutations, mapActions} from "vuex";

export default {
    name: 'AuthPage',
    data: () => ({
        userName: '',
        password: '',
    }),
    methods: {
        ...mapMutations(['setAuth']),
        ...mapActions(['getUser']),
        login() {
            let user = {
                userName: this.userName,
                password: this.password
            }
            this.getUser(user)

        }
    }
}
</script>

<style scoped>

</style>