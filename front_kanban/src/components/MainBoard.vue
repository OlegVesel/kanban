<template>
    <div class="mx-5">
        <v-row class="text-center">
            <v-col>
                <TasksList></TasksList>
            </v-col>

            <v-col>
                <TaskListWorking></TaskListWorking>
            </v-col>

            <v-col>
                <TaskListComplete></TaskListComplete>
            </v-col>
        </v-row>
    </div>
</template>

<script>
import TasksList from "@/components/TasksList.vue";
import TaskListWorking from "@/components/TaskListWorking.vue";
import TaskListComplete from "@/components/TaskListComplete.vue";
import {mapActions, mapGetters} from "vuex";

export default {

    name: 'MainBoard',
    components: {TasksList, TaskListWorking, TaskListComplete},
    data: () => ({}),
    computed: {
        ...mapGetters(['getAccount'])
    },
    methods: {
        ...mapActions(['uploadProjectTasks', 'uploadWorkingTasks', ])
    },
    beforeMount() {
        this.uploadProjectTasks(this.getAccount.id)
        this.uploadWorkingTasks(this.getAccount.id)
    }
}
</script>
