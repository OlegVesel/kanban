<template>
    <v-container>

        <v-row>
            <v-col>
                <h3>
                    Выполнено
                </h3>
            </v-col>
        </v-row>
        <v-card
                v-scroll.self="onScroll"
                class="overflow-y-auto"
                max-height="800"
                elevation="0"
                min-height="50"
                @drop="onDropInComplete($event)"
                @dragenter.prevent
                @dragover.prevent
        >
            <v-btn
                    icon
                    @click="show = !show"
            >
                <v-icon v-if="!show">
                    {{ icons.mdiChevronDoubleDown }}
                </v-icon>
                <v-icon v-else>
                    {{ icons.mdiChevronDoubleUp }}
                </v-icon>
            </v-btn>
            <template v-if="show">
                <TaskItem
                        :task="item"
                        v-for="item in getCompleteTasks"
                        :key="item.id"
                >
                </TaskItem>
            </template>

        </v-card>
    </v-container>
</template>

<script>
import TaskItem from "@/components/TaskItem.vue";
import {mapActions, mapGetters} from "vuex";
import {mdiChevronDoubleUp, mdiChevronDoubleDown} from '@mdi/js'

export default {
    name: 'TasksListComplete',
    components: {TaskItem},
    data: () => ({
        show: false,
        scrollInvoked: 0,
        icons: {
            mdiChevronDoubleUp, mdiChevronDoubleDown
        }
    }),
    watch: {
        show() {
            if (this.show)
                this.uploadCompleteTasks(this.getAccount.id)
        }
    },
    computed: {
        ...mapGetters(['getCompleteTasks', 'getAccount']),
    },
    methods: {
        ...mapActions(['updateTaskAction', 'uploadCompleteTasks']),
        onScroll() {
            this.scrollInvoked++
        },
        onDropInComplete(event) {
            let result = event.dataTransfer.getData('task')
            let task = JSON.parse(result)
            task.status = 2
            this.updateTaskAction(task)
        },
    },
}
</script>


<style scoped>

</style>