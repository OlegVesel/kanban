<template>
    <v-container>
        <v-row>
            <v-col>
                <h3>
                    В работе
                </h3>
            </v-col>
        </v-row>
        <v-card
                v-scroll.self="onScroll"
                class="overflow-y-auto"
                max-height="800"
                elevation="0"
                min-height="50"
                @drop="onDropInWorking($event)"
                @dragenter.prevent
                @dragover.prevent

        >
            <TaskItem
                    v-for="item in getWorkingTasks"
                    :key="item.id"
                    :task="item"
            >
            </TaskItem>
        </v-card>

    </v-container>
</template>

<script>
import TaskItem from "@/components/TaskItem.vue";
import {mapActions, mapGetters} from "vuex";

export default {
    name: 'TasksListWorking',
    components: {TaskItem},
    data: () => ({
        scrollInvoked:0,
    }),
    computed: {
        ...mapGetters(['getWorkingTasks'])
    },
    methods: {
        ...mapActions(['updateTaskAction']),
        onScroll() {
            this.scrollInvoked++
        },
        onDropInWorking(event){
            let result = event.dataTransfer.getData('task')
            let task = JSON.parse(result)
            task.status = 1
            this.updateTaskAction(task)
        }
    },
}
</script>


<style scoped>

</style>