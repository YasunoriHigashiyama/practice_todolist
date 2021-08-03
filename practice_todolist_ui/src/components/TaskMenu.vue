<template>
  <div class="text-center">
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs" v-on="on" icon>
          <v-icon> mdi-dots-horizontal</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item @click="dialogOpen">
          <v-list-item-icon
            ><v-icon>mdi-square-edit-outline</v-icon></v-list-item-icon
          ><v-list-item-content>
            <v-list-item-title>Edit</v-list-item-title>
          </v-list-item-content></v-list-item
        >

        <v-list-item
          @click="event(item.title)"
          v-for="(item, index) in items"
          :key="index"
        >
          <v-list-item-icon
            ><v-icon>{{ item.icon }}</v-icon></v-list-item-icon
          ><v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-menu>
    <CreateTaskDialog ref="createTaskDialog" :task="task" @addTask="edit" />
  </div>
</template>

<script>
import CreateTaskDialog from "./CreateTaskDialog.vue";
export default {
  components: { CreateTaskDialog },
  props: {
    task: {
      type: Object,
      require: false,
      default: () => {},
    },
  },
  data: () => ({
    selectedItem: 1,
    items: [{ title: "Delete", color: "error", icon: "mdi-trash-can-outline" }],
  }),
  methods: {
    event(title) {
      if (title == "Edit") {
        this.$emit("edit");
      }
      if (title == "Delete") {
        this.$emit("delete");
      }
    },
    edit(task) {
      this.$emit("addTask", task);
    },
    dialogOpen() {
      this.$refs.createTaskDialog.open();
    },
  },
};
</script>