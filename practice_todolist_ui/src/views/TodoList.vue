<template>
  <v-container>
    <v-row>
      <v-col>
        <v-row class="mt-4 pb-1">
          <div class="text-h3 text--secondary" style="letter-spacing: 2px">
            ToDo List
          </div>
        </v-row>
        <div class="mt-4 pb-1">
          <ToolBar ref="option" @addTask="addTask" @option="emitOption" />
        </div>
        <v-divider></v-divider>

        <v-row class="mt-3">
          <div v-for="task in tasks" :key="task.title">
            <TaskCard
              :ref="task.id"
              :task="task"
              @edit="editTask"
              @delete="deleteTask(task.id)"
              @addTask="addTask"
              @updateStatus="updateStatus"
            />
          </div>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
/* eslint no-unused-vars: 0 */
import ToolBar from "../components/ToolBar.vue";
import TaskCard from "../components/Task.vue";
export default {
  components: { ToolBar, TaskCard },
  data() {
    return {
      tasks: [],
      taskTemplate: {
        id: "",
        title: "",
        start: "",
        limit: "",
        memo: "",
        status: false,
      },
    };
  },
  mounted() {
    this.getTasks();
  },
  methods: {
    getTasks() {
      let option = this.getOption();
      this.$axios
        .get("/tasks", { params: option })
        .then((response) => {
          if (response.status == 200) {
            this.tasks = response.data.data;
          }
        })
        .catch((e) => {
          this.store_setAlert({
            action: true,
            message: "error \n" + e,
            detail: "タスクの取得に失敗しました",
          });
        });
    },

    addTask(task) {
      this.$axios
        .post("/task", task)
        .then((response) => {
          if (response.status == 200) {
            this.getTasks();
          }
        })
        .catch((e) => {
          this.store_setAlert({
            action: true,
            message: "error \n" + e,
            detail: "タスクの追加に失敗しました。",
          });
        });
    },
    editTask(task) {
      this.$axios
        .put("/task/" + task.id, task)
        .then((response) => {
          if (response.status == 200) {
            this.getTasks();
          }
        })
        .catch((e) => {
          this.store_setAlert({
            action: true,
            message: "error \n" + e,
            detail: "タスクの編集に失敗しました。[id]:" + task.id,
          });
        });
    },
    emitOption(option) {
      //optionが変更されたときに呼ばれる
      this.getTasks();
    },
    getOption() {
      //オプションを取得してくる
      return this.$refs.option.getOption();
    },
    deleteTask(ref) {
      var id = this.$refs[ref][0].task.id;
      this.$axios
        .delete("/task/" + id, { id: id })
        .then((response) => {
          if (response.status == 200) {
            this.getTasks();
          }
        })
        .catch((e) => {
          this.store_setAlert({
            action: true,
            message: "error \n" + e,
            detail: "タスクの削除に失敗しました。[id]:" + id,
          });
        });
    },
    updateStatus(task) {
      let param = {};
      param["id"] = task.id;
      param["status"] = task.status;
      this.$axios
        .patch("/task/" + task.id + "/status", param)
        .then((response) => {
          if (response.status == 200) {
            this.getTasks();
          }
        })
        .catch((e) => {
          this.store_setAlert({
            action: true,
            message: "error \n" + e,
            detail: "タスクのステータスの更新に失敗しました。[id]:" + task.id,
          });
        });
    },
    store_setAlert({ action, message, detail }) {
      this.$store.dispatch("alert/setAlert", {
        action: action,
        message: message,
        detail: detail,
      });
    },
  },
};
</script>
