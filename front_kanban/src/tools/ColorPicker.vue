<template>
    <v-card>
        <v-color-picker
                dot-size="25"
                v-model="colorComp"
                mode="hexa"
        ></v-color-picker>
        <v-card-actions>
            <v-row class="ma-0" justify="center">
                <v-btn
                        color="success"
                        @click="$emit('color', colorComp)"
                        small
                        class="mx-2"
                >
                    выбрать
                </v-btn>
                <v-btn
                        color="error"
                        @click="$emit('close')"
                        small
                        class="mx-2"
                >
                    отмена
                </v-btn>
                <v-btn
                        color="primary"
                        @click="$emit('clear')"
                        small
                        icon
                        class="ml-2"
                >
                    <v-icon>{{icons.mdiCloseCircle}}</v-icon>
                </v-btn>
            </v-row>
        </v-card-actions>
    </v-card>

</template>

<script>
import {mdiCloseCircle} from '@mdi/js'
export default {
    name: "ColorPicker",
    props: {
        color: {
            type: String,
            require: false,
            default: '',
        }
    },
    data: () => ({
        icons:{
            mdiCloseCircle
        },
        hexa: '#ffffffff',
    }),
    computed: {
        colorComp: {
            get() {
                return this['hexa']
            },
            set(v) {
                this['hexa'] = v
            },
        }
    },
    watch: {
        color(newVal) {
            this.colorComp = newVal
        }
    },
    beforeMount() {
        if (this.color !== '' && this.color !== null)
            this.colorComp = this.color
    }
}
</script>

<style scoped>

</style>