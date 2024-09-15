<template>
    <v-card>
        <v-color-picker
                dot-size="25"
                swatches-max-height="200"
                v-model="selectedColor"
                mode="hexa"
        ></v-color-picker>
        <v-card-actions>
            <v-btn
                color="primary"
                @click="$emit('color', selectedColor)"
                small
            >
                выбрать
            </v-btn>
            <v-btn
                    color="error"
                    @click="$emit('close')"
                    small
            >
                отмена
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
export default {
    name: "ColorPicker",
    props:{
        color:{
            type: String,
            require: false,
            default:'',
        }
    },
    data : ()=> ({
        selectedColor: '',
    }),
    watch:{
        color(newVal){
            this.selectedColor = newVal
        }
    },
    beforeMount() {
        if (this.color !== '')
            this.selectedColor = this.color
    }
}
</script>

<style scoped>

</style>