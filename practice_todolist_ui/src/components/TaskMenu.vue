<template>
  <div class="text-center">
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs" v-on="on" icon>
          <v-icon> mdi-dots-horizontal</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item @click="dialog = true">
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
    <CreateTaskDialog
      :dialog="dialog"
      :task="task"
      @addTask="edit"
      @cancel="dialog = false"
    />
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
    dialog: false,
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
      this.dialog = false;
      this.$emit("addTask", task);
    },
  },
};
</script>