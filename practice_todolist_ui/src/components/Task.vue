
<template>
  <v-col cols="12">
    <v-card class="mx-auto" width="344">
      <v-row class="mr-3">
        <v-card-subtitle class="ml-3"> {{ copyTask.id }}</v-card-subtitle>
        <v-spacer></v-spacer
        ><TaskMenu :task="task" @edit="edit" @delete="del" @addTask="addTask"
      /></v-row>
      <v-card-title class="pt-0">
        <v-checkbox
          v-model="copyTask.status"
          @change="updateStatus($event)"
          class="mb-4"
          color="primary"
          hide-details
        ></v-checkbox>
        {{ copyTask.title }}
      </v-card-title>

      <v-card-subtitle>
        Start: {{ copyTask.start }} <br />Limit: {{ copyTask.limit }}
      </v-card-subtitle>
      <v-row class="ml-3"> </v-row>
      <v-card-actions>
        <v-btn color="orange lighten-2" text> Memo </v-btn>

        <v-spacer></v-spacer>

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text>
            {{ copyTask.memo }}
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-col>
</template>

<script>
import TaskMenu from "./TaskMenu.vue";
export default {
  props: {
    task: {
      type: Object,
      require: false,
      default: () => {},
    },
  },
  components: { TaskMenu },
  data() {
    return {
      show: false,
      copyTask: {},
    };
  },
  computed: {},
  mounted() {
    this.copyTask = Object.assign({}, this.task);
  },
  methods: {
    edit(task) {
      this.$emit("edit", task);
    },
    del() {
      this.$emit("delete");
    },
    addTask(task) {
      this.$emit("addTask", task);
    },
    updateStatus() {
      this.$emit("updateStatus", this.copyTask);
    },
  },
};
</script>
