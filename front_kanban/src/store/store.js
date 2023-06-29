import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        projectTasks: [],
        workingTasks: [],
        completeTasks: [],
        isAuth:false,
        account: {}
    },
    getters: {
        getProjectTasks(state) {
            return state.projectTasks
        },
        getWorkingTasks(state) {
            return state.workingTasks
        },
        getCompleteTasks(state) {
            return state.completeTasks
        },
        isAuth(state){
            return state.isAuth
        },
        getAccount(state){
            return state.account
        }
    },
    mutations: {
        setAuth(state, status){
            state.isAuth = status
        },
        setProjectTasks(state, list) {
            state.projectTasks = list
        },
        setWorkingTasks(state, list) {
            state.workingTasks = list
        },
        setCompleteTasks(state, list) {
            state.completeTasks = list
        },
        addTaskToList(state, item) {
            if (item.status === 0) {
                state.projectTasks.push(item)
            }
            if (item.status === 1)
                state.workingTasks.push(item)
            if (item.status === 2)
                state.completeTasks.push(item)
        },
        updateTaskInList(state, item) {
            if (item.status === 0) {
                let index = state.projectTasks.findIndex(i => i.id === item.id);
                if (index === -1) {
                    state.projectTasks.push(item)
                } else {
                    Vue.set(state.projectTasks, index, item)
                }
            }
            if (item.status === 1) {
                let index = state.workingTasks.findIndex(i => i.id === item.id);
                if (index === -1) {
                    state.workingTasks.push(item)
                } else {
                    Vue.set(state.workingTasks, index, item)
                }
            }
            if (item.status === 2) {
                let index = state.completeTasks.findIndex(i => i.id === item.id);
                if (index === -1) {
                    state.completeTasks.push(item)
                } else {
                    Vue.set(state.completeTasks, index, item)
                }
            }
            //удаляем из проектов при изменении статуса
            let findProjectIndex = state.projectTasks.findIndex(i => i.id === item.id);
            if (findProjectIndex !== -1 && item.status !== 0) {
                state.projectTasks = [
                    ...state.projectTasks.slice(0, findProjectIndex),
                    ...state.projectTasks.slice(findProjectIndex + 1)
                ]
            }
            //удаляем из рабочих при изменении статуса
            let findWorkingIndex = state.workingTasks.findIndex(i => i.id === item.id);
            if (findWorkingIndex !== -1 && item.status !== 1) {
                state.workingTasks = [
                    ...state.workingTasks.slice(0, findWorkingIndex),
                    ...state.workingTasks.slice(findWorkingIndex + 1)
                ]
            }
            //удаляем из выполненных при изменении статуса
            let findCompleteIndex = state.completeTasks.findIndex(i => i.id === item.id);
            if (findCompleteIndex !== -1 && item.status !== 2) {
                state.completeTasks = [
                    ...state.completeTasks.slice(0, findCompleteIndex),
                    ...state.completeTasks.slice(findCompleteIndex + 1)
                ]
            }
        },
        deleteFromList(state, item) {
            console.log('deleted')
            if (item.status === 0) {
                let index = state.projectTasks.findIndex(i => i.id === item.id);
                state.projectTasks = [
                    ...state.projectTasks.slice(0, index),
                    ...state.projectTasks.slice(index + 1)
                ]
            }
            if (item.status === 1) {
                let index = state.workingTasks.findIndex(i => i.id === item.id);
                state.workingTasks = [
                    ...state.workingTasks.slice(0, index),
                    ...state.workingTasks.slice(index + 1)
                ]
            }
            if (item.status === 2) {
                let index = state.completeTasks.findIndex(i => i.id === item.id);
                state.completeTasks = [
                    ...state.completeTasks.slice(0, index),
                    ...state.completeTasks.slice(index + 1)
                ]
            }
        },
        setAccount(state, account){
            state.account = account
        }
    },
    actions: {
        async uploadProjectTasks({commit}, idUser) {
            let response = await fetch('/api/task/status/0/' + idUser);
            if (response.status === 200) {
                let result = await response.json()
                commit('setProjectTasks', result)
            } else if (response.status === 204) {
                commit('setProjectTasks', [])
            }
        },
        async uploadWorkingTasks({commit}, idUser) {
            let response = await fetch('/api/task/status/1/' + idUser);
            if (response.status === 200) {
                let result = await response.json()
                commit('setWorkingTasks', result)
            } else if (response.status === 204) {
                commit('setWorkingTasks', [])
            }
        },
        async uploadCompleteTasks({commit}, idUser) {
            let response = await fetch('/api/task/status/2/' + idUser);
            if (response.status === 200) {
                let result = await response.json()
                commit('setCompleteTasks', result)
            } else if (response.status === 204) {
                commit('setCompleteTasks', [])
            }
        },
        async addTask({commit}, newTask) {
            let response = await fetch('/api/task', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(newTask)
            })
            if (response.status === 200) {
                let result = await response.json()
                commit('addTaskToList', result)
            }
        },
        async updateTaskAction({commit}, task) {
            let response = await fetch('/api/task', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(task)
            })
            if (response.status === 200) {
                let result = await response.json()
                commit('updateTaskInList', result)
            }
        },
        async hardDelete({commit}, task) {
            let response = await fetch('/api/task/hard/' + task.id, {
                method: 'DELETE'
            });
            if (response.status === 200)
                commit('deleteFromList', task)
        },
        async getUser({commit}, user){
            let response = await fetch('/api/user/check', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({userName : user.userName, password : user.password})
            })
            if (response.status === 200){
                let result = await response.json()
                commit('setAccount', result)
                commit('setAuth', true)
            }
        }
    },
    modules: {}
})
